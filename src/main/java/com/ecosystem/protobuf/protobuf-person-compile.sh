cd $(dirname $0)
SRC_DIR=.
DST_DIR=.
protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/Person.proto