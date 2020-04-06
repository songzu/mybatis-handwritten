package com.songzuedu.mybatis.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public class InterceptorChain {

    private static final List<Interceptor> interceptors = new ArrayList<>();

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    /**
     * 对被拦截对象进行层层代理
     *
     * @param target
     * @return
     */
    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public boolean hasPlugin() {
        if (interceptors.size() == 0) {
            return false;
        }
        return true;
    }

}
