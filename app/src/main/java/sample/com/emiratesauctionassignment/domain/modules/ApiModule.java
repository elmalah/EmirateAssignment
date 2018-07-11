package sample.com.emiratesauctionassignment.domain.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sample.com.emiratesauctionassignment.BuildConfig;

/**
 * Created by amr elmalah on 5/6/16.
 */
@Module
public class ApiModule {

    Context context;

    public ApiModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        try {
            return new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request;
                            // Customize the request
                                request = original.newBuilder()
                                        .header("Content-Type", "application/json")
                                        .build()

                                ;

                            System.out.println(request.url().toString());
                            Response response = chain.proceed(request);
                            response.cacheResponse();
                            if (response.code() != 200 && response.code() != 201
                                    && response.code() != 204 && response.code() != 403/* success case but no content found */) {
                                return null;
                            }
                            // Customize or return the response

                            return response;
                        }
                    })
//                    .sslSocketFactory(getSSLSocketFactory())

//                    .hostnameVerifier(new HostnameVerifier()
//                    {
//                        @Override
//                        public boolean verify(String hostname,SSLSession arg1)
//                        {
//                            if (! hostname.equalsIgnoreCase("www.asdasdad.com"))
//                                return true;
//                            else
//                                return false;
//                        }
//                    })
                    .build()
                    ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    private SSLSocketFactory getSSLSocketFactory()
//            throws Exception {
//        //CertificateFactory cf = CertificateFactory.getInstance("X.509");
//        //InputStream caInput = context.getResources().openRawResource(R.raw.your_cert); // your certificate file
//        /*Certificate ca = cf.generateCertificate(caInput);
//        caInput.close();*/
//        KeyStore keyStore = KeyStore.getInstance("BKS");
//        keyStore.load(null, null);
//        //keyStore.setCertificateEntry("ca", ca);
//        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//        tmf.init(keyStore);
//        TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());
//
//        final SSLContext sslContext = SSLContext.getInstance("SSL");
//        sslContext.init(null, wrappedTrustManagers, new java.security.SecureRandom());
//        // Create an ssl socket factory with our all-trusting manager
//      /*  sslSocketFactory = sslContext.getSocketFactory();
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, wrappedTrustManagers, null);*/
//        return sslContext.getSocketFactory();
//    }
//
//    private TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {
//        final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];
//        return new TrustManager[]{
//                new X509TrustManager() {
//                    public X509Certificate[] getAcceptedIssuers() {
//                        return originalTrustManager.getAcceptedIssuers();
//                    }
//
//                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                        try {
//                            originalTrustManager.checkClientTrusted(certs, authType);
//                        } catch (CertificateException ignored) {
//                        }
//                    }
//
//                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                        try {
//                            originalTrustManager.checkServerTrusted(certs, authType);
//                        } catch (CertificateException ignored) {
//                        }
//                    }
//                }
//        };
//    }


    @Provides
    @Singleton
    Retrofit provideCall(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
//                .client(getUnsafeOkHttpClient())
//                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addConverterFactory(new Gson())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Picasso providePicasso(OkHttpClient okHttpClient) {

        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                        Log.e("Picasso", "Failed to load image:" + uri);
                    }
                })
                .build();


        return picasso;
    }


    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
/*******************************************************************************/
//    private static OkHttpClient getUnsafeOkHttpClient() {
//        try {
//            // Create a trust manager that does not validate certificate chains
//            final TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//                        }
//
//                        @Override
//                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                            return new java.security.cert.X509Certificate[]{};
//                        }
//                    }
//            };
//
//            // Install the all-trusting trust manager
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//            // Create an ssl socket factory with our all-trusting manager
//            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
//            builder.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    if( hostname.equalsIgnoreCase("www.mahami.co"))
//                    return true;
//                    else
//                        return false;
//                }
//            });
//
//            OkHttpClient okHttpClient = builder.build();
//            return okHttpClient;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }




}