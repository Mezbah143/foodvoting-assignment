#!/usr/bin/env sh

set -eu

GRADLE_VERSION="8.10.2"
BASE_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
GRADLE_USER_HOME="${GRADLE_USER_HOME:-"$BASE_DIR/.gradle"}"
DIST_DIR="$GRADLE_USER_HOME/wrapper/dists/gradle-$GRADLE_VERSION-bin"
ZIP_FILE="$DIST_DIR/gradle-$GRADLE_VERSION-bin.zip"
GRADLE_HOME="$DIST_DIR/gradle-$GRADLE_VERSION"
GRADLE_BIN="$GRADLE_HOME/bin/gradle"

if [ ! -x "$GRADLE_BIN" ]; then
  mkdir -p "$DIST_DIR"
  if [ ! -f "$ZIP_FILE" ]; then
    echo "Downloading Gradle $GRADLE_VERSION..."
    curl -L -o "$ZIP_FILE" "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip"
  fi
  echo "Installing Gradle $GRADLE_VERSION..."
  unzip -q "$ZIP_FILE" -d "$DIST_DIR"
fi

exec "$GRADLE_BIN" "$@"
