package com.uploadfiles.plugin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.NonNull;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicMarkableReference;

@NativePlugin
public class uploadFiles extends Plugin {

    @PluginMethod
    public void uploadFirebaseStorageFile(final PluginCall call) throws MalformedURLException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        File dir = this.getContext().getFilesDir();

        final String fileNewName = call.getString("fileNewName");
        final String fileLocalName = call.getString("fileLocalName");
        final String fileFirestorageURL = call.getString("fileFirestorageURL");
        final JSObject ret = new JSObject();

        Uri file = Uri.fromFile(new File(dir, fileLocalName));
        StorageReference riversRef = storageRef.child(fileFirestorageURL + fileNewName);
        UploadTask uploadTask = riversRef.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask
            .addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        String stackTrace = Log.getStackTraceString(exception);
                        ret.put("response", stackTrace);
                        call.error(stackTrace);
                    }
                }
            )
            .addOnSuccessListener(
                new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        ret.put("response", "success");
                        call.success(ret);
                    }
                }
            );
    }

    @PluginMethod
    public void getStorageDownloadUrl(final PluginCall call) {
        final String value = call.getString("url");
        final JSObject ret = new JSObject();

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference dateRef = storageRef.child(value);
        dateRef
            .getDownloadUrl()
            .addOnSuccessListener(
                new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUrl) {
                        ret.put("response", downloadUrl);
                        call.success(ret);
                        //do something with downloadurl
                    }
                }
            );
    }
}
