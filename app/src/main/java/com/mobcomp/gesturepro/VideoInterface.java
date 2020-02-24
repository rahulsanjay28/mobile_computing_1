package com.mobcomp.gesturepro;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface VideoInterface {
    @Multipart
    @POST("upload_video.php")
    Call<ResponseBody> uploadVideoToServer(@PartMap Map<String, RequestBody> map,
                                           /*@Part("group_id") RequestBody groupId,
                                           @Part("id") RequestBody asuId,*/
                                           @Part("accept") int accept);
}
