package com.example.podcastserver.util;

public interface QueryContent {

    /**
     * MAIN QUERY IN THIS PROJECT
     */
    String mainQuery = "SELECT * FROM (select t.typname as name from pg_type t join pg_enum e on t.oid = e.enumtypid GROUP BY t.typname) enum_names NATURAL FULL JOIN (select i.table_name as name from information_schema.tables i where i.table_schema = 'public') table_names NATURAL FULL JOIN (select p.proname as name from pg_proc p left join pg_namespace n on p.pronamespace = n.oid left join pg_language l on p.prolang = l.oid where n.nspname not in ('pg_catalog', 'information_schema','pgagent') and l.lanname='plpgsql' and substring(p.proname,1,1) != '_' and p.proname notnull order by name) functions_name;";

    /**
     * SELECT QUERY
     */

    /**
     * INSERT AND UPDATE TABLES QUERY
     */

    /**
     * ENUMS CREATE QUERY
     */

    /**
     * TABLES CREATE QUERY
     */

    /**
     * FUNCTIONS CREATE QUERY
     */
}
