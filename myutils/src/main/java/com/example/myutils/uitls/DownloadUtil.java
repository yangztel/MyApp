package com.example.myutils.uitls;

/**
 * retrofit 下载器，还需要修改
 *
 * Created by Longwj on 2018/4/24 0024.
 */

public class DownloadUtil {
//    private static final String TAG = "DownloadUtil";
//
//    private static final int DEFAULT_TIMEOUT = 15;
//
//    private Retrofit retrofit;
//
//    private FileDownloadListener listener;
//
//    private String baseUrl;
//
//    private String downloadUrl;
//
//    public DownloadUtil(String baseUrl, FileDownloadListener listener) {
//
//        this.baseUrl = baseUrl;
//        this.listener = listener;
//
//        FileDownloadInterceptor mInterceptor = new FileDownloadInterceptor(listener);
//
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(mInterceptor)
//                .retryOnConnectionFailure(true)
//                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .build();
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .client(httpClient)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//    }
//
//    /**
//     * 开始下载
//     *
//     * @param url
//     * @param filePath
//     * @param subscriber
//     */
//    public void download(@NonNull String url, final String filePath, Subscriber subscriber) {
//
//        listener.onStartDownload();
//
//        // subscribeOn()改变调用它之前代码的线程
//        // observeOn()改变调用它之后代码的线程
//        retrofit.create(CommonService.class)
//                .getUpdateAPK(url)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .map(new Func1<ResponseBody, InputStream>() {
//
//                    @Override
//                    public InputStream call(ResponseBody responseBody) {
//                        return responseBody.byteStream();
//                    }
//                })
//                .observeOn(Schedulers.computation()) // 用于计算任务
//                .doOnNext(new Action1<InputStream>() {
//                    @Override
//                    public void call(InputStream inputStream) {
//
//                        writeFile(inputStream, filePath);
//
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//    }
//
//    /**
//     * 将输入流写入文件
//     *
//     * @param inputString
//     * @param filePath
//     */
//    private void writeFile(InputStream inputString, String filePath) {
//
//        File file = new File(filePath);
//        if (file.exists()) {
//            file.delete();
//        }
//
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//
//            byte[] b = new byte[1024];
//
//            int len;
//            while ((len = inputString.read(b)) != -1) {
//                fos.write(b,0,len);
//            }
//            inputString.close();
//            fos.close();
//
//        } catch (FileNotFoundException e) {
//            listener.onFail("FileNotFoundException");
//        } catch (IOException e) {
//            listener.onFail("IOException");
//        }
//
//    }
}
