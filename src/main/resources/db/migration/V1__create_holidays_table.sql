CREATE TABLE public.holidays (
    id SERIAL PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL
);

INSERT INTO public.holidays (country, name, date) VALUES
('Country1', 'Holiday1', '2024-07-09'),
('Country2', 'Holiday2', '2024-07-10'),
('Country3', 'Holiday3', '2024-07-11');
