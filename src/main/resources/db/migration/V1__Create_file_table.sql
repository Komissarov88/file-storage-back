CREATE TABLE public.file_info_metadata
(
    hash       character varying(64)  NOT NULL,
    file_name  character varying(255) NOT NULL,
    subtype    int                    NOT NULL,
    size_bytes int                    NOT NULL,
    create_time       timestamp              NOT NULL,
    PRIMARY KEY (hash)
)
    WITH (
        OIDS = FALSE
    );
