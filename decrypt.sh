#!/bin/sh

# Decrypt the file
# --batch to prevent interactive command
# --yes to assume "yes" for questions
gpg --quiet --batch --yes --decrypt --passphrase="$FIREBASE_DECRYPTION_KEY" \
--output $HOME/src/main/resources/test.json $HOME/src/main/resources/firebase.json.gpg
