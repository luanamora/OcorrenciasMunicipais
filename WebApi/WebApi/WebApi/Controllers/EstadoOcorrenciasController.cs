using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EstadoOcorrenciasController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public EstadoOcorrenciasController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/EstadoOcorrencias
        [HttpGet]
        public IEnumerable<EstadoOcorrencia> GetEstadoOcorrencia()
        {
            return _context.EstadoOcorrencia;
        }

        // GET: api/EstadoOcorrencias/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetEstadoOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var estadoOcorrencia = await _context.EstadoOcorrencia.FindAsync(id);

            if (estadoOcorrencia == null)
            {
                return NotFound();
            }

            return Ok(estadoOcorrencia);
        }

        // PUT: api/EstadoOcorrencias/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutEstadoOcorrencia([FromRoute] int id, [FromBody] EstadoOcorrencia estadoOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != estadoOcorrencia.CdEstadoocorrencia)
            {
                return BadRequest();
            }

            _context.Entry(estadoOcorrencia).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EstadoOcorrenciaExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/EstadoOcorrencias
        [HttpPost]
        public async Task<IActionResult> PostEstadoOcorrencia([FromBody] EstadoOcorrencia estadoOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.EstadoOcorrencia.Add(estadoOcorrencia);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetEstadoOcorrencia", new { id = estadoOcorrencia.CdEstadoocorrencia }, estadoOcorrencia);
        }

        // DELETE: api/EstadoOcorrencias/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteEstadoOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var estadoOcorrencia = await _context.EstadoOcorrencia.FindAsync(id);
            if (estadoOcorrencia == null)
            {
                return NotFound();
            }

            _context.EstadoOcorrencia.Remove(estadoOcorrencia);
            await _context.SaveChangesAsync();

            return Ok(estadoOcorrencia);
        }

        private bool EstadoOcorrenciaExists(int id)
        {
            return _context.EstadoOcorrencia.Any(e => e.CdEstadoocorrencia == id);
        }
    }
}