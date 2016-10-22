
println(strcat(strcat(probeClass, "."), probeMethod));

@OnMethod(clazz="+com.vip.demo.Filter", method="doFilter")

@OnMethod(clazz="@javax.jws.WebService", method="@javax.jws.WebMethod")

@OnMethod(clazz="java.net.ServerSocket", method="<init>")

@OnMethod( clazz="java.net.ServerSocket", method="bind" )

@OnMethod(clazz = "java.net.ServerSocket", method = "getLocalPort", location = @Location(Kind.RETURN))
public static void onGetLocalPort(@Return int port, @Duration long duration) {
}
