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
    public class PrioridadeOcorrenciasController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public PrioridadeOcorrenciasController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/PrioridadeOcorrencias
        [HttpGet]
        public IEnumerable<PrioridadeOcorrencia> GetPrioridadeOcorrencia()
        {
            return _context.PrioridadeOcorrencia;
        }

        // GET: api/PrioridadeOcorrencias/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetPrioridadeOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var prioridadeOcorrencia = await _context.PrioridadeOcorrencia.FindAsync(id);

            if (prioridadeOcorrencia == null)
            {
                return NotFound();
            }

            return Ok(prioridadeOcorrencia);
        }

        // PUT: api/PrioridadeOcorrencias/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPrioridadeOcorrencia([FromRoute] int id, [FromBody] PrioridadeOcorrencia prioridadeOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != prioridadeOcorrencia.CdPrioridade)
            {
                return BadRequest();
            }

            _context.Entry(prioridadeOcorrencia).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PrioridadeOcorrenciaExists(id))
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

        // POST: api/PrioridadeOcorrencias
        [HttpPost]
        public async Task<IActionResult> PostPrioridadeOcorrencia([FromBody] PrioridadeOcorrencia prioridadeOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.PrioridadeOcorrencia.Add(prioridadeOcorrencia);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetPrioridadeOcorrencia", new { id = prioridadeOcorrencia.CdPrioridade }, prioridadeOcorrencia);
        }

        // DELETE: api/PrioridadeOcorrencias/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePrioridadeOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var prioridadeOcorrencia = await _context.PrioridadeOcorrencia.FindAsync(id);
            if (prioridadeOcorrencia == null)
            {
                return NotFound();
            }

            _context.PrioridadeOcorrencia.Remove(prioridadeOcorrencia);
            await _context.SaveChangesAsync();

            return Ok(prioridadeOcorrencia);
        }

        private bool PrioridadeOcorrenciaExists(int id)
        {
            return _context.PrioridadeOcorrencia.Any(e => e.CdPrioridade == id);
        }
    }
}