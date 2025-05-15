package com.rjasw.demo.spring_security.msg;


import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames=true)
@Getter @Accessors(chain = true)
public class Response<T> {


    protected T data ;
    protected HttpStatus httpStatus;
    protected String errmsg ;

    static public <V> Response<V> ok(V data) {
        return ok(data,HttpStatus.OK);
    }

    static public <V> Response<V> ok(HttpStatus status, String msg) {
        return new Response<V>((V)null, status, msg);
    }

    static public <V> Response<V> ok(V data, HttpStatus status) {
        return new Response<V>(data,status, null);
    }

    static public <V> Response<V> ko(String msg) {
        return ko(msg,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static public <V> Response<V> ko(String msg, HttpStatus status) {
        return new Response<V>((V)null,status,msg);
    }

    public ResponseEntity<Response<T>> toResponseEntity() {
        return new ResponseEntity<Response<T>>(this, this.httpStatus);
    }

	public <V> Response(V data2, HttpStatus status, Object object) {
		// TODO Auto-generated constructor stub
	}

    public String toJsonString() {
        return new JSONObject()
                .put("status",httpStatus.value())
                .put("errmsg",errmsg)
                .put("data",data)
                .toString() ;
    }


}
