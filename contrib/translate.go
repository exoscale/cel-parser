package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"strings"

	"google.golang.org/protobuf/encoding/protojson"
	"google.golang.org/protobuf/encoding/prototext"

	spb "github.com/google/cel-spec/proto/test/v1/testpb"
)

func translateProtoFile(inpath, outpath, infile string) error {

	outfile := strings.ReplaceAll(infile, "textproto", "json")
	dat, err := os.ReadFile(strings.Join([]string{inpath, infile}, "/"))
	if err != nil {
		return err
	}
	var m spb.SimpleTestFile
	err = prototext.Unmarshal(dat, &m)
	if err != nil {
		return err
	}
	dat, err = protojson.Marshal(&m)
	if err != nil {
		return err
	}
	err = os.WriteFile(strings.Join([]string{outpath, outfile}, "/"), dat, 0644)
	if err != nil {
		return err
	}
	fmt.Println("translated", infile)
	return nil
}

func main() {
	if len(os.Args) < 2 {
		log.Fatal("Need an arg pointing to a directory containing test files")
	}
	inpath := os.Args[1]
	outpath := "."
	if len(os.Args) >= 3 {
		outpath = os.Args[2]
	}
	files, err := ioutil.ReadDir(inpath)
	if err != nil {
		log.Fatal(err)
	}
	for _, f := range files {
		if strings.HasSuffix(f.Name(), ".textproto") {
			translateProtoFile(inpath, outpath, f.Name())
		}
	}

}
