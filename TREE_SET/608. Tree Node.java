/*

    Solution

    Approach I: Using UNION [Accepted]

    Intuition

    We can print the node type by judging every record by its definition in this table. Root: it does not have a parent node at all Inner: it is the parent node of some nodes, and it has a not NULL parent itself. * Leaf: rest of the cases other than above two

    Algorithm

    By transiting the node type definition, we can have the following code.

    For the root node, it does not have a parent.

///////////////////////////////////////////////////////////////////////////////
    SELECT
        id, 'Root' AS Type
    FROM
        tree
    WHERE
        p_id IS NULL
    For the leaf nodes, they do not have any children, and it has a parent.

    SELECT
        id, 'Leaf' AS Type
    FROM
        tree
    WHERE
        id NOT IN (SELECT DISTINCT
                p_id
            FROM
                tree
            WHERE
                p_id IS NOT NULL)
            AND p_id IS NOT NULL
///////////////////////////////////////////////////////////////////////////////            
    For the inner nodes, they have have some children and a parent.

///////////////////////////////////////////////////////////////////////////////
    SELECT
        id, 'Inner' AS Type
    FROM
        tree
    WHERE
        id IN (SELECT DISTINCT
                p_id
            FROM
                tree
            WHERE
                p_id IS NOT NULL)
            AND p_id IS NOT NULL
///////////////////////////////////////////////////////////////////////////////            
    So, one solution to the problem is to combine these cases together using UNION.

///////////////////////////////////////////////////////////////////////////////
    SELECT
        id, 'Root' AS Type
    FROM
        tree
    WHERE
        p_id IS NULL

    UNION

    SELECT
        id, 'Leaf' AS Type
    FROM
        tree
    WHERE
        id NOT IN (SELECT DISTINCT
                p_id
            FROM
                tree
            WHERE
                p_id IS NOT NULL)
            AND p_id IS NOT NULL

    UNION

    SELECT
        id, 'Inner' AS Type
    FROM
        tree
    WHERE
        id IN (SELECT DISTINCT
                p_id
            FROM
                tree
            WHERE
                p_id IS NOT NULL)
            AND p_id IS NOT NULL
    ORDER BY id;
///////////////////////////////////////////////////////////////////////////////    
    Approach II: Using flow control statement CASE [Accepted]

    Algorithm

    The idea is similar with the above solution but the code is simpler by utilizing the flow control statements, which is effective to output differently based on different input values. In this case, we can use CASE statement.

    MySQL
///////////////////////////////////////////////////////////////////////////////
    SELECT
        id AS `Id`,
        CASE
            WHEN tree.id = (SELECT atree.id FROM tree atree WHERE atree.p_id IS NULL)
              THEN 'Root'
            WHEN tree.id IN (SELECT atree.p_id FROM tree atree)
              THEN 'Inner'
            ELSE 'Leaf'
        END AS Type
    FROM
        tree
    ORDER BY `Id`;
///////////////////////////////////////////////////////////////////////////////    
    Approach III: Using IF function [Accepted]

    Algorithm

    Also, we can use a single IF function instead of the complex flow control statements.

    MySQL
///////////////////////////////////////////////////////////////////////////////
    SELECT
        tree.id,
        IF(ISNULL(tree.p_id),
            'Root',
            IF(tree.id IN (SELECT p_id FROM tree), 'Inner','Leaf')) Type
    FROM
        tree tree
///////////////////////////////////////////////////////////////////////////////        
 */
