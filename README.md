gutendownload
-------------

This project helps to download books that had been split into multiple html files like seen
on https://www.projekt-gutenberg.org/kafka/verwandl/index.html. To download all the files manually takes quite some time
and recursive download of all links on the page is download too much. So this application reads the index.html and
downloads only the linked files in the index. These downloaded files are then concatenated into a single file on local
disk.

Configuration is done in the `GutenDownloadApplication.kt` directly at the moment. So the last mile to have all configs
coming in via args has been skipped because it was already working. If you want to use this and need a way to configure
it from cli get in touch via issue tracker.

All texts for testing included in this project have been taken from https://www.projekt-gutenberg.org. Tests are using
wiremock to fake http requests so that we don't have to put any load on https://www.projekt-gutenberg.org. This and
speed are the sole reasons for having texts included.