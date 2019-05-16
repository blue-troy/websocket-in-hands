package club.bluetroy;

/**
 * @author heyixin 2019-05-16 11:26.
 */
public interface MessageResolver {

    Object resolve(Object input, IOHandler ioHandler);
}
