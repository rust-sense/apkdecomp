#!/usr/bin/env bash

gh act \
  --platform ubuntu-latest=catthehacker/ubuntu:act-latest \
  --artifact-server-path artifacts \
  --cache-server-path cache \
  --secret-file .secrets \
  --input version=0.0.32 \
  --input create_release=false \
  "$@"
