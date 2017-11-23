package com.exposit.carsharing.cloud;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;

import java.io.IOException;
import java.io.InputStream;

public class DropboxClient implements CloudStorageClient {
    private DbxClientV2 client;

    public DropboxClient(String appKey, String accessToken) {
        client = new DbxClientV2(new DbxRequestConfig(appKey), accessToken);
    }

    public void uploadFile(InputStream file, String path) throws IOException, DbxException {
        client.files().uploadBuilder(path).withMode(WriteMode.OVERWRITE).uploadAndFinish(file);
    }

    public String createSharedLink(String path) throws DbxException {
        String url;
        try {
            url = client.sharing().createSharedLinkWithSettings(path).getUrl();
        } catch (CreateSharedLinkWithSettingsErrorException e) {
            return getExistSharedLink(path);
        }
        // Look at https://cantonbecker.com/etcetera/2014/how-to-directly-link-or-embed-dropbox-images/
        url = url.replace("?dl=0", "?raw=1");

        return url;
    }

    private String getExistSharedLink(String path) throws DbxException {
        return client.sharing()
                .listSharedLinksBuilder().withPath(path).withDirectOnly(true).start()
                .getLinks().get(0).getUrl();
    }
}