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
    public class HistoricoOcorrenciasController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public HistoricoOcorrenciasController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/HistoricoOcorrencias
        [HttpGet]
        public IEnumerable<HistoricoOcorrencia> GetHistoricoOcorrencia()
        {
            return _context.HistoricoOcorrencia;
        }

        // GET: api/HistoricoOcorrencias/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetHistoricoOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var historicoOcorrencia = await _context.HistoricoOcorrencia.FindAsync(id);

            if (historicoOcorrencia == null)
            {
                return NotFound();
            }

            return Ok(historicoOcorrencia);
        }

        // PUT: api/HistoricoOcorrencias/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutHistoricoOcorrencia([FromRoute] int id, [FromBody] HistoricoOcorrencia historicoOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != historicoOcorrencia.CdHistoricoocorrencia)
            {
                return BadRequest();
            }

            _context.Entry(historicoOcorrencia).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!HistoricoOcorrenciaExists(id))
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

        // POST: api/HistoricoOcorrencias
        [HttpPost]
        public async Task<IActionResult> PostHistoricoOcorrencia([FromBody] HistoricoOcorrencia historicoOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.HistoricoOcorrencia.Add(historicoOcorrencia);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetHistoricoOcorrencia", new { id = historicoOcorrencia.CdHistoricoocorrencia }, historicoOcorrencia);
        }

        // DELETE: api/HistoricoOcorrencias/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteHistoricoOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var historicoOcorrencia = await _context.HistoricoOcorrencia.FindAsync(id);
            if (historicoOcorrencia == null)
            {
                return NotFound();
            }

            _context.HistoricoOcorrencia.Remove(historicoOcorrencia);
            await _context.SaveChangesAsync();

            return Ok(historicoOcorrencia);
        }

        private bool HistoricoOcorrenciaExists(int id)
        {
            return _context.HistoricoOcorrencia.Any(e => e.CdHistoricoocorrencia == id);
        }
    }
}