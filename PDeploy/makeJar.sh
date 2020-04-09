#!/bin/bash

set -e
set -u

echo ""

7z a -tzip Deploy/PDiceMOO.jar ../PDiceMOO/bin/ch
7z a -tzip Deploy/PDiceGUI.jar ../PDiceGUI/bin/ch

echo ""

