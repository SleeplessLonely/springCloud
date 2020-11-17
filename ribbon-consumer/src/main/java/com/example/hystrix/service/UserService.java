package com.example.hystrix.service;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.hystrix.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand
	public User getUserById(Long id) {
		// 同步返回结果
		return restTemplate.getForObject("http://eureka-client/users/{1}", User.class, id);
	}

	@HystrixCommand
	public Future<User> getUserByIdAsync(final Long id) {
//		Future<User> queue = new UserCommand(restTemplate, 1L).queue();
		// 异步返回结果
		return new AsyncResult() {
			@Override
			public Object invoke() {
				return restTemplate.getForObject("http://eureka-client/users/{1}", User.class, id);
			}
		};
	}
	// 返回过个结果集
	@HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
	public Observable<User> getUserByIdEager(final Long id) {
		return Observable.create(new OnSubscribe<User>() {
			@Override
			public void call(Subscriber<? super User> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						User user = restTemplate.getForObject("http://eureka-client/users/{1}", User.class, id);
						observer.onNext(user);
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		});
	}
	// 返回多个操作结果
	@HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY)
	public Observable<User> getUserByIdLazy(final Long id) {
		return Observable.create(new OnSubscribe<User>() {
			@Override
			public void call(Subscriber<? super User> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						User user = restTemplate.getForObject("http://eureka-client/users/{1}", User.class, id);
						observer.onNext(user);
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		});
	}
}
