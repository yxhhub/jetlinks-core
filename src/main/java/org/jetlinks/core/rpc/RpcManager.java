package org.jetlinks.core.rpc;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * RPC服务管理器,统一管理注册RPC接口
 *
 * @author zhouhao
 * @since 1.2
 */
public interface RpcManager {

    /**
     * 当前集群节点ID
     *
     * @return 当前集群节点ID
     */
    String currentServerId();

    /**
     * 注册RPC服务实现类,可调用返回值{@link  Disposable#dispose()}来注销服务
     *
     * @param rpcService 服务
     * @param <T>        服务实现类
     * @return dispose
     */
    <T> Disposable registerService(T rpcService);

    /**
     * 注册指定id标识的RPC服务实现类,可调用返回值{@link  Disposable#dispose()}来注销服务
     *
     * @param rpcService 服务
     * @param <T>        服务实现类
     * @return dispose
     */
    <T> Disposable registerService(String serviceId, T rpcService);

    /**
     * 获取全部指定接口的服务
     *
     * @param service 服务接口类
     * @param <I>     接口类型
     * @return RPC接口
     */
    <I> Flux<RpcService<I>> getServices(Class<I> service);

    /**
     * 选择一个服务
     *
     * @param service 服务类型
     * @param <I>     服务类型
     * @return 选择结果
     */
    <I> Mono<RpcService<I>> selectService(Class<I> service);

    /**
     * 获取指定服务ID的RPC服务接口
     *
     * @param serviceId 服务ID
     * @param service   RPC接口
     * @param <I>       服务接口类
     * @return RPC接口
     */
    <I> Flux<RpcService<I>> getServices(String serviceId, Class<I> service);

    /**
     * 获取指定节点ID的RPC服务接口,用于进行点对点调用
     *
     * @param serverNodeId 集群节点ID
     * @param service      RPC接口
     * @param <I>          服务接口类
     * @return RPC接口
     */
    <I> Mono<I> getService(String serverNodeId,
                           Class<I> service);

    /**
     * 获取指定节点ID的指定服务ID的RPC服务接口,用于进行点对点调用
     *
     * @param serverNodeId 集群节点ID
     * @param service      RPC接口
     * @param serviceId    服务ID
     * @param <I>          服务接口类
     * @return RPC接口
     */
    <I> Mono<I> getService(String serverNodeId,
                           String serviceId,
                           Class<I> service);


    /**
     * 监听服务注册，注销事件
     *
     * @param service 服务接口类
     * @param <I>     服务接口类型
     * @return 事件流
     */
    <I> Flux<ServiceEvent> listen(Class<I> service);


}
