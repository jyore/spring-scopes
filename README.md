# spring-scopes
Custom Spring Bean Scopes for those situations where the defaults just won't do

Available Scopes
* Route Scope - Scope providing per-route-execution scoping
* Page Scope - Scope providing per-page scoping
* Thread Scope - Scope providing per-thread scoping
* Inherited Thread Scope - Scope providing per-thread-and-child-thread scoping


In order to use custom scopes you must register the scope with Spring.  This can be done via Java Config or XML Config as shown below. In both examples, the `scope-name` string should be replaced with the string you will use to reference that scope and the `CustomScope` class should be the class of the scope you are adding.


Java Config Example

    @Configuration
    public class ScopeConfig {

        @Bean
        public CustomScopeConfigurer customScopeConfigurer() {
            CustomScopeConfigurer configurer = new CustomScopeConfigurer();

            Map<String,Object> scopes = new HashMap<String,Object>();
            scope.put("scope-name",new CustomScope());

            configurer.setScopes(scopes);
            return configurer;
        }
    }



XML Config Example

    <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
      <property name="scopes">
        <map>
          <entry key="scope-name"><bean class="com.example.CustomScope"/></entry>
        </map>
      </property>
    </bean>



## Route Scope
Scope a bean by a route's execution. Beans are bound by the initial exchange and maintained even through splits and aggregations.

In order to use Page Scope, you must register the scope with Spring's CustomScopeConfigurer and then register the notification service. This service manages setting the Exchange context

    <bean id="exchange-scope-event" class="com.jyore.spring.scope.route.ExchangeEventNotifier"/>


## Page Scope
The page scope allows per-page or even per-page-and-page-parameters scoping. This type of scoping could be useful when needing to provide per-page static or semi-static data caching (i.e. JSON data). It could also be used to manage a page that is being edited by multiple users simultaneously (i.e. Google Docs).

In order to use Page Scope, you must register the scope with Spring's CustomScopeConfigurer and also register the PageScopeFilter in your servlet filter chain (i.e. in your web.xml or configure with Spring). 


## Thread Scope
The thread scope simply scopes beans per-thread.  With this scope, brean creation is done per thread. **NOTE**: Children threads will get new beans


## Inherited Thread Scope
The inherited thread scope simply scopes beans per-thread.  With this scope, brean creation is done per thread. If a thread spawns children threads, they will also be covered in the scope
