import React, { useEffect, useState } from "react";
import { Table } from "reactstrap";
import api from "../services/api";

export default function MatchesListing() {
    const [matches, setMatches] = useState(null);

    useEffect(() => {
        async function fetchMatches() {
            try {
                const response = await api.get("/matches");
                setMatches(response.data);
            } catch (err) {
                console.error("error", err);
            }
        }
        fetchMatches();
    }, []);

    if (matches === null) return null;          // todavía cargando

    if (matches.length === 0) return <h1>NO MATCHES</h1>;

    return (
        <Table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Start</th>
                    <th>Finish</th>
                    <th>White</th>
                    <th>Black</th>
                    <th>Winner</th>
                </tr>
            </thead>
            <tbody>
                {matches.map((m) => (
                    <tr key={m.id}>
                        <td>{m.name}</td>
                        <td>{m.start}</td>
                        <td>{m.finish}</td>
                        <td>{m.whitePlayer?.username}</td>
                        <td>{m.blackPlayer?.username}</td>
                        <td>{m.winner?.username}</td>
                    </tr>
                ))}
            </tbody>
        </Table>
    );
}
