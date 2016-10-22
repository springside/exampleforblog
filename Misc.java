import java.lang.reflect.Field;

import com.sun.btrace.AnyType;

@BTrace
public class DemoTracer2 {
	
	private static Field fdFiled = field("java.io,FileInputStream", "fd");

	private static Field portField = field(classForName("com.vip.demo.MyObject", contextClassLoader()), "port");

	@TLS
	private static int port = -1;

@OnMethod(clazz="+com.vip.demo.Filter", method="doFilter")

@OnMethod(clazz="@javax.jws.WebService", method="@javax.jws.WebMethod")

@OnMethod(clazz="java.net.ServerSocket", method="<init>")

@OnMethod( clazz="java.net.ServerSocket", method="bind" )
public static mythond(){
}

@OnMethod(clazz = "java.net.ServerSocket", method = "getLocalPort", location = @Location(Kind.RETURN))
public static void onGetLocalPort(@Return int port, @Duration long duration) {
    println(strcat(strcat(probeClass, "."), probeMethod));
}

@OnMethod(clazz = "java.net.ServerSocket", method = "bind", location = @Location(Kind.ERROR))
public static void onBind(Throwable exception, @Duration long duration){
}

@OnMethod(clazz = "java.net.ServerSocket", method = "bind", location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/", where = Where.AFTER))
public static void onBind2(@Self Object self, @TargetInstance Object instance, @TargetMethodOrField String method, @Duration long duration) {
}


@OnMethod(clazz = "/javax\\.swing\\..*/", method = "/.*/", location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/", where = Where.AFTER))
public static void onBind3(@Self Object self, @TargetInstance Object instance, @TargetMethodOrField String method,
			@Duration long duration) {
}

	@OnMethod(clazz = "java.net.ServerSocket", location = @Location(value = Kind.LINE, line = 363))
	public static void onBind4() {
		println("socket bind reach line:363");
	}

	@OnMethod(clazz = "java.io.File", method = "createTempFile", location = @Location(value = Kind.RETURN))
	public static void o(@Self Object self, String prefix, String suffix, @Return AnyType result) {

	}

	public static void onChannelRead(@Self Object self) {
		println("port:" + getInt(portField, self));
	}

	@OnMethod(clazz = "java.net.ServerSocket", method = "<init>")
	public static void onServerSocket(int p) {
		port = p;
	}

	@OnMethod(clazz = "java.net.ServerSocket", method = "<init>", location = @Location(Kind.RETURN))
	public static void onSockReturn() {
		println("server socket at " + port);
	}
	
	@OnMethod(clazz = "+com.vip.demo.Filter", method = "doFilter", location = @Location(Kind.RETURN))
	public static void onDoFilter2(@ProbeClassName String pcn,  @Duration long duration) {
		if (duration > 1000000) {
			println(pcn + ",duration:" + (duration / 100000));
		}
	}
	
	@OnMethod(clazz = "java.lang.System", method = "gc")
	public static void onSystemGC() {
		println("entered System.gc()");
		jstack();
	}
    
}
