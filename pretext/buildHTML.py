#!/usr/bin/env python3
# -*- coding: utf-8 -*-

__author__ = "Andrew Rechnitzer"
__copyright__ = "Copyright (C) 2020 Andrew Rechnitzer"
__credits__ = ["Andrew Rechnitzer"]
__license__ = "GPL-3.0-or-later"

import os
import subprocess
import lxml.etree as ET

# source file
sourceFile = "./clp_3_mc.ptx"
# xslt pretext file
xsltFile = "/home/andrew/Projects/mathbook/xsl/mathbook-html.xsl"
# the schema to check against
xs = ET.RelaxNG(ET.parse("/home/andrew/Projects/mathbook/schema/pretext.rng"))
# mbx location
mbx = "/home/andrew/Projects/mathbook/script/mbx"
# output directory
outDir = "./site"

# each in this list should be a 4-ple [ancestor-tag, tag, replace-before, replace-after]
myDescTags = [
    ["answer", "proof", "<p><term>Proof:</term></p>", "<p><m>\square</m></p>"],
    ["solution", "proof", "<p><term>Proof:</term></p>", "<p><m>\square</m></p>"],
]

# each tag should be a 3-ple [tag, replace-before, replace-after]
myTags = [
    ["answerproof", "<p><term>Proof:</term></p>", "<p><m>\square</m></p>",],
]

myRep = [
    ["hint", "statement"],
    ["answer", "statement"],
    ["solution", "statement"],
]

mySubs = [
    ["conceptual", "<p><alert>Exercises &#8212; Stage 1</alert></p>"],
    ["procedural", "<p><alert>Exercises &#8212; Stage 2</alert></p>"],
    ["application", "<p><alert>Exercises &#8212; Stage 3</alert></p>"],
    ["fromexam", "<em>&#x2733;</em>"],
]

# build parameters as dict
param = {
    "exercise.divisional.answer": "'no'",
    "exercise.divisional.hint": "'no'",
    "exercise.divisional.solution": "'no'",
}


# if tg is descendant of atg then replace it with pre + post.
def replaceDescTag(src, atg, tg, pre, post):
    # do replacements in reverse so as to not mess up indices as we insert
    for repTag in reversed(src.findall("//{}//{}".format(atg, tg))):
        par = repTag.getparent()
        parIndex = par.index(repTag)
        # insert the post-string after the current reptag.
        par.insert(parIndex + 1, ET.fromstring(post))
        # then move up all the child-nodes - in reverse order so as to not mess up indices
        for child in reversed(repTag):
            par.insert(parIndex, child)
        # now insert the pre-string before the reptag
        par.insert(parIndex, ET.fromstring(pre))
        # finally remove the reptag.
        par.remove(repTag)
    return src


# replace tg with pre + post.
def replaceTag(src, tg, pre, post):
    # do replacements in reverse so as to not mess up indices as we insert
    for repTag in reversed(src.findall("//{}".format(tg))):
        par = repTag.getparent()
        parIndex = par.index(repTag)
        # insert the post-string after the current reptag.
        par.insert(parIndex + 1, ET.fromstring(post))
        # then move up all the child-nodes - in reverse order so as to not mess up indices
        for child in reversed(repTag):
            par.insert(parIndex, child)
        # now insert the pre-string before the reptag
        par.insert(parIndex, ET.fromstring(pre))
        # finally remove the reptag.
        par.remove(repTag)
    return src


def replaceSubsTag(src, tg, sb):
    for repTag in reversed(src.findall("//{}".format(tg))):
        par = repTag.getparent()
        parIndex = par.index(repTag)
        # now insert the pre-string before the reptag
        par.insert(parIndex, ET.fromstring(sb))
        # finally remove the reptag.
        par.remove(repTag)
    return src


def repTag(src, tg, sb):
    print("Replacing tag {} with {}".format(tg, sb))
    for repTag in src.findall("//{}".format(tg)):
        repTag.tag = sb
    return src


def userTags(src):
    # for [tg, sb] in myRep:
    #     src = repTag(src, tg, sb)
    for [tg, sb] in mySubs:
        src = replaceSubsTag(src, tg, sb)
    for [tg, pre, post] in myTags:
        src = replaceTag(src, tg, pre, post)
    for [atg, tg, pre, post] in myDescTags:
        src = replaceDescTag(src, atg, tg, pre, post)
    return src


print("Reading in source file")
# read in the source file
src = ET.parse(sourceFile)
# and its xincludes
try:
    src.xinclude()
except Exception as err:
    print(">>> ERROR <<< ")
    print(err)
    exit(1)

# now process any user tags
print("Processing any user tags")
try:
    procd = userTags(src)
except Exception as err:
    print(">>> ERROR <<< ")
    print(err)
    exit(1)

# try to validate before building
print("Validate before building")
try:
    if xs.validate(procd):
        print("\tSource is valid")
    else:
        print("\tValidation problems:")
        print(xs.error_log)
except Exception as err:
    print(">>> ERROR <<< ")
    print(err)
    exit(1)

# all passed so now build

# read in the pretext xslt magic
print("Read in xsl file")
xslt = ET.parse(xsltFile)

# and build the transform
print("Load the transform")
transform = ET.XSLT(xslt)
os.chdir(outDir)

# apply the pretext transforms to the processed-src
print("Transform the source")
htmlSource = transform(procd, **param)
print("HTML written")
print("Processing tikz to svg")
subprocess.check_output(
    [mbx, "-vv", "-c", "latex-image", "-f", "svg", "-d", "images", "../" + sourceFile]
)

print("Error log:")
print(transform.error_log)
