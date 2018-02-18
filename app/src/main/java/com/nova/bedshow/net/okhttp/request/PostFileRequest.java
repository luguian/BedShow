package com.nova.bedshow.net.okhttp.request;

import com.nova.bedshow.net.okhttp.utils.Exceptions;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/10.
 */

public class PostFileRequest extends OkHttpRequest {

    private static MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");

    private File file;
    private MediaType mediaType;


    public PostFileRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, File file, MediaType mediaType)
    {
        super(url, tag, params, headers);
        this.file = file;
        this.mediaType = mediaType;

        if (this.file == null)
        {
            Exceptions.illegalArgument("the file can not be null !");
        }
        if (this.mediaType == null)
        {
            this.mediaType = MEDIA_TYPE_STREAM;
        }

    }

    @Override
    protected RequestBody buildRequestBody()
    {
        return RequestBody.create(mediaType, file);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody)
    {
        return builder.post(requestBody).build();
    }

}
