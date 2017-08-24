package com.github.baseclass.rx;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by fanxl2 on 2016/7/25.
 */
public class RxResultHelper {

	public static <T> Observable.Transformer<ExampleResponse<T>, T> handleResult(){
		return new Observable.Transformer<ExampleResponse<T>, T>() {
			@Override
			public Observable<T> call(Observable<ExampleResponse<T>> apiResponseObservable) {
				return apiResponseObservable.flatMap(
						new Func1<ExampleResponse<T>, Observable<T>>() {
							@Override
							public Observable<T> call(ExampleResponse<T> tApiResponse) {
								if (tApiResponse==null){
									return Observable.empty();
								}else if (tApiResponse.isSuccess()){
									return createData(tApiResponse.getResult());
								}else{
									return Observable.error(new ServerException(tApiResponse.getMessage()+""));
								}
							}
						});
			}
		};
	}
	/*public static <T> Observable.Transformer<ExampleResponse<T>, T> handleResult(){
		return apiResponse -> apiResponse.flatMap(
				new Func1<ExampleResponse<T>, Observable<T>>() {
					@Override
					public Observable<T> call(ExampleResponse<T> response) {
						if (response==null){
							return Observable.empty();
						}else if (response.isSuccess()){
							T res = response.getResult();
							Class<?> responseClass = res.getClass();
//							Class baseObjClass=BaseObj.class;
                            Class ListClass=List.class;
							boolean assignableFromBaseObj = ListClass.isAssignableFrom(responseClass);
//                            boolean assignableFromList = ListClass.isAssignableFrom(responseClass);
							if(assignableFromBaseObj){
								return returnDataForMsg(res,response.getErrMsg());
							}else{
								return returnData(response.getResponse());
							}
						}else{
							return Observable.error(new ServerException(response.getErrMsg()+""));
						}
					}
				});
	}
	private static <T> Observable<T> returnData(final T result) {
		return Observable.create(subscriber -> {
			try {
				subscriber.onNext(result);
				subscriber.onCompleted();
			}catch (Exception e){
				subscriber.onError(e);
			}
		});
	}
	private static <T> Observable<T> returnDataForMsg(final T result, final String msg) {
		return Observable.create(subscriber -> {
			try {
				if(result!=null){
					((BaseObj)result).setMsg(msg);
					subscriber.onNext(result);
				}else{
					T newResult = (T) new BaseObj();
					((BaseObj)newResult).setMsg(msg);
					subscriber.onNext(newResult);
				}
				subscriber.onCompleted();
			}catch (Exception e){
				subscriber.onError(e);
			}
		});
	}*/
	public static <T> Observable.Transformer<ExampleResponse<T>, T> hand(){

		return new Observable.Transformer<ExampleResponse<T>, T>() {
			@Override
			public Observable<T> call(Observable<ExampleResponse<T>> apiResponseObservable) {

				return apiResponseObservable.flatMap(new Func1<ExampleResponse<T>, Observable<T>>() {
					@Override
					public Observable<T> call(ExampleResponse<T> tApiResponse) {

						return null;
					}
				});
			}
		};
	}

	public static <T> Observable.Transformer<ExampleResponse<T>, T> handleNoErrorResult(){
		return new Observable.Transformer<ExampleResponse<T>, T>() {
			@Override
			public Observable<T> call(Observable<ExampleResponse<T>> apiResponseObservable) {

				return apiResponseObservable.flatMap(
						new Func1<ExampleResponse<T>, Observable<T>>() {
							@Override
							public Observable<T> call(ExampleResponse<T> tApiResponse) {
								if (tApiResponse==null){
									return Observable.empty();
								}else if (tApiResponse.isSuccess()){
									return createData(tApiResponse.getResult());
								}else if (tApiResponse.getResultNo()==-1){
									return createData(tApiResponse.getResult());
								}else{
									return Observable.error(new ServerException(tApiResponse.getMessage()+""));
								}
							}
						});
			}
		};
	}

	private static <T> Observable<T> createData(final T result) {
		return Observable.create(new Observable.OnSubscribe<T>() {
			@Override
			public void call(Subscriber<? super T> subscriber) {
				try {
					subscriber.onNext(result);
					subscriber.onCompleted();
				}catch (Exception e){
					subscriber.onError(e);
				}
			}
		});
	}

}
