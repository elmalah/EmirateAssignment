package sample.com.emiratesauctionassignment.domain.controller;


import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;
import sample.com.emiratesauctionassignment.domain.models.AttendParam;
import sample.com.emiratesauctionassignment.domain.models.ChangePasswordParam;
import sample.com.emiratesauctionassignment.domain.models.CommentParam;
import sample.com.emiratesauctionassignment.domain.models.DeliverTaskParam;
import sample.com.emiratesauctionassignment.domain.models.EntityModel;
import sample.com.emiratesauctionassignment.domain.models.ForgetPasswordParam;
import sample.com.emiratesauctionassignment.domain.models.LoginModel;
import sample.com.emiratesauctionassignment.domain.models.LoginParam;
import sample.com.emiratesauctionassignment.domain.models.PostResponseModel;
import sample.com.emiratesauctionassignment.domain.models.PostResponseModelX;
import sample.com.emiratesauctionassignment.domain.models.TaskDetailsModel;
import sample.com.emiratesauctionassignment.domain.models.TasksModel;
import sample.com.emiratesauctionassignment.domain.models.TrackParam;


/**
 * Created by elmalah on 2/25/2017.
 */

public class WSController {
    public interface Login {
        @POST("pub/get/user/login")
        Observable<LoginModel> getLoginInfo(@Body LoginParam loginParam);

        @POST("pub/get/user/reset/password")
        Observable<PostResponseModel> sendForgetPasswordEmail(@Body ForgetPasswordParam forgetPasswordParam);

        @PUT("usr/put/user/password")
        Observable<PostResponseModelX> sendChangePasswordEmail(@Body ChangePasswordParam changePasswordParam);

        @POST("usr/post/user/geo/arr")
        Observable<PostResponseModel> sendTrack(@Body ArrayList<TrackParam> trackParam);
    }

    public interface Tasks {

        @GET("usr/get/user/{userId}/tasks/page/{pageNumber}")
        Observable<TasksModel> getTasksList(@retrofit2.http.Path("userId") String userId,
                                            @retrofit2.http.Path("pageNumber") int pageNumber,
                                            @Query("access_token") String access_token,
                                            @Query("lang") String lang);

        @GET("usr/get/user/{userId}/history/page/{pageNumber}")
        Observable<TasksModel> getTasksHistoryList(@retrofit2.http.Path("userId") String userId,
                                                   @retrofit2.http.Path("pageNumber") int pageNumber,
                                                   @Query("access_token") String access_token,
                                                   @Query("lang") String lang);

        @GET("usr/get/task/details/{id}")
        Observable<TaskDetailsModel> getTaskDetails(
                @retrofit2.http.Path("id") int id,
                @Query("access_token") String access_token,
                @Query("lang") String lang);

        @PUT("usr/put/user/task")
        Observable<PostResponseModel> deliverTask(@Body DeliverTaskParam deliverTaskParam);

//        @PUT("usr/put/user/{userId}/task/{taskId}")
//        Observable<PostResponseModel> deliverTask(@retrofit2.http.Path("userId") String userId,
//                                                  @retrofit2.http.Path("taskId") String taskId,
//                @Body DeliverTaskParam deliverTaskParam);

        @PUT("usr/put/user/task/comment")
        Observable<PostResponseModel> sendComment(@Body CommentParam commentParam);

        @POST("usr/post/user/attend")
        Observable<PostResponseModel> attend(@Body AttendParam attendParam);
    }
    public interface Auctions {
        @GET()
        Observable<EntityModel> getAuctionsList();
    }
}
