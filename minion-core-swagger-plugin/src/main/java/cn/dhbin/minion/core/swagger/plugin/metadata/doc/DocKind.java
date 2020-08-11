package cn.dhbin.minion.core.swagger.plugin.metadata.doc;

/**
 * @author donghaibin
 * @date 2020/8/6
 */
public enum DocKind {

    /**
     * an attribute in an HTML element
     */
    ATTRIBUTE,
    /**
     * {@literal @author}
     */
    AUTHOR,
    /**
     * {@literal @code}
     */
    CODE,
    /**
     * An embedded HTML comment.
     * {@literal <!-- text -->}
     */
    COMMENT,
    /**
     * {@code @deprecated}
     */
    DEPRECATED,
    /**
     * The top level representation of a documentation comment.
     */
    DOC_COMMENT,
    /**
     * A tree node for an @docroot inline tag.
     */
    DOC_ROOT,
    /**
     * A tree node for the end of an HTML element. like {@literal </p>}
     */
    END_ELEMENT,
    /**
     * A tree node for an HTML entity.
     */
    ENTITY,
    /**
     * A tree node to stand in for a malformed text
     */
    ERRONEOUS,
    /**
     * A tree node for an @exception or @throws block tag.
     */
    EXCEPTION,
    /**
     * An identifier in a documentation comment.
     */
    IDENTIFIER,
    /**
     * A tree node for an @inheritDoc inline tag.
     */
    INHERIT_DOC,
    /**
     * A tree node for an @link or @linkplain inline tag.
     */
    LINK,
    /**
     *
     */
    LINK_PLAIN,
    LITERAL,
    PARAM,
    REFERENCE,
    RETURN,
    SEE,
    SERIAL,
    SERIAL_DATA,
    SERIAL_FIELD,
    SINCE,
    START_ELEMENT,
    TEXT,
    THROWS,
    UNKNOWN_BLOCK_TAG,
    UNKNOWN_INLINE_TAG,
    VALUE,
    VERSION,
    OTHER;

}
