package com.cn.ahelp3.core.util;

public class HttpDefinition {
    /** The HTTP <b>100 Continue</b> informational status response code indicates that everything
     * so far is OK and that the client should continue with the request or ignore it
     * if it is already finished.<br>
     * To have a server check the request's headers, a client must send
     * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Expect">Expect</a> :
     * <b>100-continue</b> as a header in its initial request and receive a <b>100-continue</b>
     * status code in response before sending the body.*/
    public static final int CONTINUE = 100;

    /** The HTTP <b>101 Switching Protocols</b> response code indicates the protocol the server
     * is switching to as requested by a client which sent the message including the
     * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Upgrade">Upgrade</a>
     * request header.<br>
     * The server includes in this response an
     * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Upgrade">Upgrade</a>
     * response header to indicate the protocol it switched to. The process is described
     * in detail in the article
     * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Protocol_upgrade_mechanism">Protocol upgrade mechanism</a>. */
    public static final int SWITCHING_PROTOCOLS = 101;

    public static String getName(int statusCode) {
        switch (statusCode) {
            case CONTINUE: return "Continue";
            case SWITCHING_PROTOCOLS: return "Switching Protocols";
            default: return "Unknown status";
        }
    }
}
