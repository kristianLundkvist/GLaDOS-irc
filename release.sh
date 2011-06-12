#!/bin/bash
mkdir -v release
ant -f build.xml
cp -v GLaDOS.jar release/
cp -Rv lib/ release/
tar -vpczf Release.tar.gz release/
