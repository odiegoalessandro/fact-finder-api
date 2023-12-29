CREATE TABLE FACT_TABLE(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255),
    body VARCHAR(255),
    source VARCHAR(200)
)