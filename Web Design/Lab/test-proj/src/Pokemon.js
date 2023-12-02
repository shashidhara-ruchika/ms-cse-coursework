import React from 'react';
  
export const Pokemon = ({ pokemon }) => {
  return (
    <div className="card outlined px-50 py-30" style={{ width: '900px' }}>
      {pokemon && (
        <>
          <div className="card-header">
            <h4 className="card-title">Pokemon Details</h4>
          </div>
          <div className="card-body">
            <div>
              <p>
                <strong>ID: </strong>
                <span data-testid="pokemon-id">{pokemon.id}</span>
              </p>
              <p>
                <strong>Name: </strong>
                <span data-testid="pokemon-name">{pokemon.name}</span>
              </p>
              <p>
                <strong>Height: </strong>
                <span data-testid="pokemon-height">{pokemon.height}</span>
              </p>
              <p>
                <strong>Weight: </strong>
                <span data-testid="pokemon-weight">{pokemon.weight}</span>
              </p>
            </div>
          </div>
        </>
      )}
    </div>
  );
};