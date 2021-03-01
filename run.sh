if ! which java; then
  apt-get -y update
  apt-get -y install default-jre
  curl -O https://download.clojure.org/install/linux-install-1.10.2.796.sh
  chmod +x linux-install-1.10.2.796.sh
  sudo ./linux-install-1.10.2.796.sh
fi

rm -rf .cpcache data
clojure -M -m foo
rm -r data/{index,lucene}
clojure -M -m foo
