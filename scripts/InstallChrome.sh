#!/bin/bash
sudo apt-get update
sudo apt-get install -y unzip wget xvfb libxi6 libgconf-2-4
set -ex
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo apt install ./google-chrome-stable_current_amd64.deb