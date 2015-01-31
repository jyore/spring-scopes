# spring-scopes
Custom Spring Bean Scopes for those situations where the defaults just won't do

## Route Scope
Scope a bean by a route's execution. Beans are bound by the initial exchange and maintained even through splits and aggregations.

In order to use Route Scope, you must first register the scope with spring

    <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
      <property name="scopes">
        <map>
          <entry key="route"><bean class="com.jyore.spring.scope.route.RouteScope"/></entry>
        </map>
      </property>
    </bean>

And then register the notification service. This service manages setting the Exchange context

    <bean id="exchange-scope-event" class="com.jyore.spring.scope.route.ExchangeEventNotifier"/>
