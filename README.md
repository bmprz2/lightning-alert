# This is DTN job interview assignment submitted by BOB PEREZ

To run:

(Windows)
```
gradlew.bat run --args="<json_filename>"
```
(OSX/Linux)
```
./gradlew run --args="<json_filename>"
```

*json_filename* is an optional argument. This is the name of the json file that will be read by the application.

Application will search for the file in **src/main/resources/input**. Default is *lightning.json*.

Examples:
```
./gradlew run
./gradlew run --args="false-lightning.json"
```

**Errors handled:**
* *unable to open file* - File can't be read.
* *invalid json format* - Line read is not of proper json format.
* *missing or invalid flash type* - Line that does not have or has unrecognized *flash type* value.
* *missing latitude* - Line is missing latitude value.
* *missing longitude* - Line is missing longitude value.

When application fails to read input file, it just exits.

When application reads a line that it can't process, it skips it proceeds to the next line.

If any of the errors above occurs, application writes an error message to the console. It also
displays the erroneous line, so it can be corrected.

**Other notes:**
1) Since heartbeat is not an actual strike, application does not print anything even when it matches an asset.
2) Conversion of latitude-longitude pair to quadkey was picked up from [https://docs.microsoft.com/en-us/bingmaps/articles/bing-maps-tile-system?redirectedfrom=MSDN].
