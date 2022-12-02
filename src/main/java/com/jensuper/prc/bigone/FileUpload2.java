package com.jensuper.prc.bigone;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.IdentityInfo;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * file
 * </p>
 *
 * @author jichao
 * @date 2022/1/10 17:20
 * @since
 */
public class FileUpload2 {

    public static void main(String[] args) throws IOException {

        downloadFile();
    }

    private static boolean downloadFile(){
        String host = "52.82.45.11";
        String user = "huijie";
        String password = "";
        String fileName = "brand_keyword.csv";
        String localFilePath = "/Users/chaoji/devlop/project/datamarket_api/tmp/";
        String remoteFilePath = "/home/huijie/";

        // without passphrase
        String keyPath = "/Users/chaoji/id_rsa";
        String passphrase = null;

        // with passphrase
        // String keyPath = "c:/cygwin64/home/woddle/.ssh/id_dsa_withpass";
        // String passphrase = "super-secrets";

        StandardFileSystemManager sysManager = new StandardFileSystemManager();

        //download der Datei
        try {
            sysManager.init();

            FileObject localFile = sysManager.resolveFile(localFilePath + fileName);

            FileObject remoteFile = sysManager.resolveFile(createConnectionString(host, user, password, keyPath, passphrase, remoteFilePath), createDefaultOptions(keyPath, passphrase));

            //Selectors.SELECT_FILES --> A FileSelector that selects only the base file/folder.
            remoteFile.copyFrom(localFile, Selectors.SELECT_FILES);
//            localFile.copyFrom(remoteFile, Selectors.SELECT_FILES);


        } catch (Exception e) {
            System.out.println("Downloading file failed: " + e.toString());
            return false;
        }finally{
            sysManager.close();
        }
        return true;
    }

    public static String createConnectionString(String hostName, String username, String password, String keyPath, String passphrase, String remoteFilePath) {
        if (keyPath != null) {
            return "sftp://" + username + "@" + hostName + "/" + remoteFilePath;
        } else {
            return "sftp://" + username + ":" + password + "@" + hostName + "/" + remoteFilePath;
        }
    }

    private static FileSystemOptions createDefaultOptions(String keyPath, String passPhrase) throws FileSystemException, FileSystemException {
        //create options for sftp
        FileSystemOptions options = new FileSystemOptions();
        //ssh key
        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(options, "no");
        //set root directory to user home
        SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(options, true);
        //timeout
        SftpFileSystemConfigBuilder.getInstance().setTimeout(options, 10000);

        if (keyPath != null) {
            IdentityInfo identityInfo = null;
            if (passPhrase != null) {
                identityInfo = new IdentityInfo(new File(keyPath), passPhrase.getBytes());
            } else {
                identityInfo = new IdentityInfo(new File(keyPath));
            }
            SftpFileSystemConfigBuilder.getInstance().setIdentityInfo(options, identityInfo);
        }
        return options;
    }
}
