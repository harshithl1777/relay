#!/bin/sh

# Decrypt the file
# --batch to prevent interactive command
# --yes to assume "yes" for questions
gpg --quiet --batch --yes --decrypt --passphrase="$PROJECT_NAME" \
--output ./src/main/resources/firebase.json ./src/main/resources/firebase.json.gpg
