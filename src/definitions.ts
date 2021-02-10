declare global {
  interface PluginRegistry {
    uploadFiles: uploadFilesPlugin;
  }
}

export interface uploadFilesPlugin {
  uploadFirebaseStorageFile(options: {
    fileNewName: string;
    fileLocalName: string;
    fileFirestorageURL: string;
  }): Promise<{
    fileNewName: string;
    fileLocalName: string;
    fileFirestorageURL: string;
  }>;
  getStorageDownloadUrl(options: { url: string }): Promise<{ url: string }>;
}
