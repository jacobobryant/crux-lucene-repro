rm -rf .cpcache data
clj -M -m foo
rm -r data/{index,lucene}
clj -M -m foo
