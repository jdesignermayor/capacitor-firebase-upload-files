# capacitor-firebase-upload-file
Capacitor plugin for Firebase Storage, With the plugin you can upload different types of files, from images and videos.
> Especially it was designed only to upload videos .mp4 videos.

The plugin supports for Android Platform not iOS and Web for now.

| Platform |   |
|----------|:-:|
| Android  |✅|
| iOS      |🧠|
| Web      |🧠|

✅ - Ready
🧠 - Thinking about, date to be defined


## How to install
Install the plugin into your Capacitor project with npm.

```
npm install --save coru-uploadfiles-plugin@0.0.1
```

## How to use

```
TypeSript

import 'coru-uploadfiles-plugin';
const { uploadFiles } = Plugins;

this.result = (
        await uploadFiles.uploadFirebaseStorageFile({
        fileLocalName: fileName,
        fileNewName: 'newfile.mp4',
        fileFirestorageURL: 'plugin/'
        })
    ).response;
```JS
