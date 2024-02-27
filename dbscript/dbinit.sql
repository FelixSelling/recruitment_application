--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2024-02-21 19:28:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 225 (class 1259 OID 22851)
-- Name: application; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.application (
    application_id integer NOT NULL,
    person_id integer,
    status_id integer
);


ALTER TABLE public.application OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 22850)
-- Name: application_application_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.application ALTER COLUMN application_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.application_application_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 22740)
-- Name: availability; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.availability (
    availability_id integer NOT NULL,
    person_id integer,
    from_date date,
    to_date date
);


ALTER TABLE public.availability OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 22743)
-- Name: availability_availability_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.availability ALTER COLUMN availability_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.availability_availability_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 22744)
-- Name: competence; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.competence (
    competence_id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.competence OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 22747)
-- Name: competence_competence_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.competence ALTER COLUMN competence_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.competence_competence_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 22748)
-- Name: competence_profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.competence_profile (
    competence_profile_id integer NOT NULL,
    person_id integer,
    competence_id integer,
    years_of_experience numeric(4,2)
);


ALTER TABLE public.competence_profile OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 22751)
-- Name: competence_profile_competence_profile_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.competence_profile ALTER COLUMN competence_profile_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.competence_profile_competence_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 22752)
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    person_id integer NOT NULL,
    name character varying(255),
    surname character varying(255),
    pnr character varying(255),
    email character varying(255),
    password character varying(255),
    role_id integer,
    username character varying(255)
);


ALTER TABLE public.person OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 22757)
-- Name: person_person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.person ALTER COLUMN person_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.person_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 22758)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 22761)
-- Name: role_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.role ALTER COLUMN role_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 227 (class 1259 OID 22857)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    status_id integer NOT NULL,
    status_number integer
);


ALTER TABLE public.status OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 22856)
-- Name: status_status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.status ALTER COLUMN status_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.status_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3214 (class 2606 OID 22855)
-- Name: application application_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.application
    ADD CONSTRAINT application_pkey PRIMARY KEY (application_id) INCLUDE (application_id);


--
-- TOC entry 3204 (class 2606 OID 22763)
-- Name: availability availability_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.availability
    ADD CONSTRAINT availability_pkey PRIMARY KEY (availability_id);


--
-- TOC entry 3206 (class 2606 OID 22765)
-- Name: competence competence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.competence
    ADD CONSTRAINT competence_pkey PRIMARY KEY (competence_id);


--
-- TOC entry 3208 (class 2606 OID 22767)
-- Name: competence_profile competence_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.competence_profile
    ADD CONSTRAINT competence_profile_pkey PRIMARY KEY (competence_profile_id);


--
-- TOC entry 3210 (class 2606 OID 22769)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (person_id);


--
-- TOC entry 3212 (class 2606 OID 22771)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- TOC entry 3216 (class 2606 OID 22861)
-- Name: status status_id_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_id_pkey PRIMARY KEY (status_id);


--
-- TOC entry 3217 (class 2606 OID 22772)
-- Name: availability availability_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.availability
    ADD CONSTRAINT availability_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(person_id);


--
-- TOC entry 3218 (class 2606 OID 22777)
-- Name: competence_profile competence_profile_competence_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.competence_profile
    ADD CONSTRAINT competence_profile_competence_id_fkey FOREIGN KEY (competence_id) REFERENCES public.competence(competence_id);


--
-- TOC entry 3219 (class 2606 OID 22782)
-- Name: competence_profile competence_profile_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.competence_profile
    ADD CONSTRAINT competence_profile_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(person_id);


--
-- TOC entry 3221 (class 2606 OID 22862)
-- Name: application person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.application
    ADD CONSTRAINT person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(person_id);


--
-- TOC entry 3220 (class 2606 OID 22787)
-- Name: person person_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(role_id);


--
-- TOC entry 3222 (class 2606 OID 22867)
-- Name: application status_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.application
    ADD CONSTRAINT status_id_fkey FOREIGN KEY (status_id) REFERENCES public.status(status_id);


-- Completed on 2024-02-21 19:28:52

--
-- PostgreSQL database dump complete
--

