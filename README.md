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

Application will search for the file in **src/main/resources/input**. Defaults to *lightning.json* if nothing is specified.

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
3) It was assumed that quadKey is unique to an asset.

**Answers to questions:**

**1) What is the time complexity for determining if a strike has occurred for a particular asset?**
*[bob] O(n) where n is the number of known strikes.*
    
**2) If we put this code into production, but found it too slow, or it needed to scale to many more users or more frequent strikes, what are the first things you would think of to speed it up?**
*[bob] This can be converted to a micro-service and deployed to the cloud in auto-scaling instances behind a load balancer. This way each user request is a separate process that is not dependent on others. If needed, deployment to multiple availability zones can also be looked at as an option to provide performant service in critical locations. Also, assets can be cached so there's no need to fetch them repeatedly. Similarly, the alerts can be put in a distributed store that is synchronized across instances. This ensures reliability that the alerts are not sent repeatedly for a particular asset.*
