package com.jensuper.prc.bigone;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.userauth.keyprovider.KeyProvider;

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
public class FileUpload {

    public static void main(String[] args) throws IOException {
        SSHClient sshClient = new SSHClient();
        sshClient.addHostKeyVerifier(new PromiscuousVerifier());
        sshClient.connect("52.82.45.11",2951);
        KeyProvider keys = sshClient.loadKeys("/Users/chaoji/id_rsa");
        sshClient.authPublickey("huijie", keys);
        SFTPClient sftpClient = sshClient.newSFTPClient();
        try {
            sftpClient.put("/Users/chaoji/devlop/project/datamarket_api/tmp/brand_keyword.csv", "/home/huijie/");
//            sftpClient.get(REMOTE_FILENAME,LOCAL_DOWNLOAD_FOLDER + "SftpMain-sshj-withKey.java");
//            sftpClient.rm(REMOTE_FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sftpClient.close();
        sshClient.disconnect();
    }
}
