package com.futurebank.order.utils.MailUtils;

import com.futurebank.pojo.utils.DateUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import javax.mail.search.*;
import java.io.*;
import java.security.NoSuchProviderException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PaperDowload {

    private static String mOutputPathStr = "";

    private static Map<String, String> mSuccessDownloadMap = new HashMap<String, String>();

    private static SimpleDateFormat mDateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    private static Map<String, Part> mAttchesOfCurrentMail = new HashMap<String, Part>();

    private static String mLogFileName;

    // String regEx =
    // "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";

    public PaperDowload() {
        try {
            Store store = getGmailImapStore("business@futurebank.global","ddntvclabkwcubol");
            Folder folder = store.getDefaultFolder();// 默认父目录
            if (folder == null) {
                System.out.println("服务器不可用");
                return;
            }
            /*
             * System.out.println("默认信箱名:" + folder.getName());
             *
             * Folder[] folders = folder.list();// 默认目录列表 for(int i = 0; i <
             * folders.length; i++) { System.out.println(folders[0].getName());
             * } System.out.println("默认目录下的子目录数: " + folders.length);
             */
            Folder popFolder = folder.getFolder("INBOX");// 获取收件箱
//            popFolder.open(Folder.READ_ONLY);// 可读邮件,可以删邮件的模式打开目录
            popFolder.open(Folder.READ_WRITE); // 必须是 READ_WRITE 模式才能修改标志
            // 4. 列出来收件箱 下所有邮件
            SearchTerm senderFilter = new FromTerm(new InternetAddress("no-reply-mpp@kasikornglobalpayment.com"));
//            SearchTerm unseenFilter = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
//            SearchTerm combinedFilter = new AndTerm(senderFilter, unseenFilter);
            Date date = DateUtils.strToDate("2024-12-25", DateUtils.DATE_PATTEN_YY_MM_DD);
            SearchTerm searchTerm = new ReceivedDateTerm(ComparisonTerm.GE, date);
            SearchTerm combinedFilter = new AndTerm(senderFilter, searchTerm);
            Message[] messages = popFolder.search(combinedFilter);

//            Message[] messages = popFolder.getMessages();
            // 取出来邮件数
            int msgCount = messages.length;
            System.out.println("共有邮件: " + msgCount + "封");
//             FetchProfile fProfile = new FetchProfile();// 选择邮件的下载模式,
//            // 根据网速选择不同的模式
//             fProfile.add(FetchProfile.Item.ENVELOPE);
//             folder.fetch(messages, fProfile);// 选择性的下载邮件
//            // 5. 循环处理每个邮件并实现邮件转为新闻的功能
            for (int i = 0; i < msgCount; i++) {
                // 得到邮件的附件内容

                mailReceiver(messages[i]);
            }
            // 7. 关闭 Folder 会真正删除邮件, false 不删除
            popFolder.close(false);
            // 8. 关闭 store, 断开网络连接
            store.close();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 解析邮件
     *
     * @param
     *
     * @param
     * @return
     * @throws IOException
     * @throws MessagingException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private void mailReceiver(Message msg) throws Exception {
        // 发件人信息
        String date = mDateFormatter.format(msg.getSentDate());
        System.out.println("邮件发送时间: " + date + "\t主题:" + msg.getSubject());
        // getContent() 是获取包裹内容, Part相当于外包装
        Object o = msg.getContent();
        // 得到该邮件的所有附件
        mAttchesOfCurrentMail.clear();
        detectAttach(o);
        // 下载所有附件
        for (String key : mAttchesOfCurrentMail.keySet()) {
            Part attchPart = mAttchesOfCurrentMail.get(key);
            // 去重
            // 判断是否存在
            String isExst = mSuccessDownloadMap.getOrDefault(key, "");
            if (isExst.isEmpty() || isExst.compareTo(date) < 0) {
                // 不存在 或者 有更新 就下载
                boolean isDownloadSuccess = dowload(attchPart);

                if (!isDownloadSuccess)
                    continue;
                mSuccessDownloadMap.put(key, date);

            }
        }
    }

    private boolean dowload(Part part) {
        try {
            if (part.getDisposition() != null) {

                String strFileNmae = MimeUtility.decodeText(part.getFileName()); // MimeUtility.decodeText解决附件名乱码问题
                InputStream in = part.getInputStream();// 打开附件的输入流
                // 读取附件字节并存储到文件中, 多个版本的文件会覆盖
                java.io.FileOutputStream out = new FileOutputStream(mOutputPathStr + "/"+strFileNmae, false);
                int data;
                while ((data = in.read()) != -1) {
                    out.write(data);
                }
                in.close();
                out.close();
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("下载失败");
        return false;
    }

    private void detectAttach(Object obj)
            throws UnsupportedEncodingException, FileNotFoundException, MessagingException, IOException {

        if (obj instanceof Multipart) {
            Multipart multipart = (Multipart) obj;
            // System.out.println("邮件共有" + multipart.getCount() + "部分组成");
            // 依次处理各个部分
            for (int j = 0, n = multipart.getCount(); j < n; j++) {
                // System.out.println("处理第" + j + "部分");
                Part partJ = multipart.getBodyPart(j);// 解包, 取出 MultiPart的各个部分,
                // 每部分可能是邮件内容,
                // 也可能是另一个小包裹(MultipPart)
                // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type:
                // multipart/alternative
                if (partJ.getContent() instanceof Multipart) {
                    Multipart p = (Multipart) partJ.getContent();// 转成小包裹
                    detectAttach(p);

                }
                detectAttach(partJ);
            }
        } else if (obj instanceof Part) {
            Part partO = (Part) obj;
            if (partO.getDisposition() != null) {
                String strFileNmae = MimeUtility.decodeText(partO.getFileName()); // MimeUtility.decodeText解决附件名乱码问题

                if (strFileNmae != null) {
                    mAttchesOfCurrentMail.put(strFileNmae, partO);
                    System.out.println("-------------------------------" + strFileNmae);
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        for (;;){
            if (args.length == 0) {
                mOutputPathStr = "/Users/liuronghua/Desktop/";
            } else {
                mOutputPathStr = args[1];
                System.out.println(args.toString());
            }
            mLogFileName = mOutputPathStr + "\\downloadLog.txt";
            initDownd(mLogFileName);

            for (int i = 0; i < mSuccessDownloadMap.size(); i++) {
                System.out.println(mSuccessDownloadMap.toString());
            }

            new PaperDowload();
        }
    }

    private static void initDownd(String logFileName) {
        BufferedReader bf;
        try {
            bf = new BufferedReader(new FileReader(logFileName));
            String line;
            while ((line = bf.readLine()) != null) {
                String[] strs = line.split("\t");
                if (strs.length == 2) {
                    mSuccessDownloadMap.put(strs[0], strs[1]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("没有历史日志文件");
            // e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件读写错误");
            e.printStackTrace();
        }

    }


    public static Store getGmailImapStore(String emailuser, String emailpassword)
            throws MessagingException {
        Session session;
        String emailserver = "imap.gmail.com";
        String emailprovider = "imaps";
        Store store = null;

        Properties props = System.getProperties();
        props.setProperty("mail.pop3s.rsetbeforequit", "true");
        props.setProperty("mail.pop3.rsetbeforequit", "true");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imaps.host", "imap.gmail.com");
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.imap.socketFactory.fallback", "false");

        session = Session.getInstance(props, null);

        store = session.getStore(emailprovider);
        store.connect(emailserver, emailuser, emailpassword);
        return store;
    }

}