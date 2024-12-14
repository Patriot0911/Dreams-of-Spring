-- for old oracle db (v < 19)
INSERT INTO tasks (
    title, description, priority, task_date, completed
)
SELECT 'Math', '2 + 2 = ?; 212/42 = ?; sqrt(101 + 2^2*5) - 12 = ?', 2, TO_DATE('2024-12-12', 'YYYY-MM-DD'), 1 FROM dual UNION ALL
SELECT 'English', 'Translate: `Пес патрон, пес патрон`', 1, TO_DATE('2024-12-13', 'YYYY-MM-DD'), 0 FROM dual UNION ALL
SELECT 'Spanish', '...', 3, TO_DATE('2024-12-14', 'YYYY-MM-DD'), 1 FROM dual UNION ALL
SELECT 'Chemistry', 'HF + H20 <=> H30 + F', 2, TO_DATE('2024-12-15', 'YYYY-MM-DD'), 0 FROM dual;
