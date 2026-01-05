#!/usr/bin/env bash

rm -rf artifacts cache

gh act \
  --platform ubuntu-latest=catthehacker/ubuntu:act-latest \
  --artifact-server-path artifacts \
  --cache-server-path cache \
  --secret-file .secrets \
  --input version=0.0.32 \
  --input create_release=false \
  "$@" | tee run-act.log

unzip artifacts/1/proto/proto.zip