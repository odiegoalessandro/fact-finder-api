CREATE TABLE FACT_TABLE(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title TEXT,
    body TEXT,
    source VARCHAR(200)
)