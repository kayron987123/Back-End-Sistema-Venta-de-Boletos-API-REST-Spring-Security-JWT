package pe.org.group02.ventaboletoscine.response;

public record ResponseConsultas<T>(int code, String error, Iterable<T> list) {
}