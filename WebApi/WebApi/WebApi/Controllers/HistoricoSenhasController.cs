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
    public class HistoricoSenhasController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public HistoricoSenhasController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/HistoricoSenhas
        [HttpGet]
        public IEnumerable<HistoricoSenha> GetHistoricoSenha()
        {
            return _context.HistoricoSenha;
        }

        // GET: api/HistoricoSenhas/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetHistoricoSenha([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var historicoSenha = await _context.HistoricoSenha.FindAsync(id);

            if (historicoSenha == null)
            {
                return NotFound();
            }

            return Ok(historicoSenha);
        }

        // PUT: api/HistoricoSenhas/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutHistoricoSenha([FromRoute] int id, [FromBody] HistoricoSenha historicoSenha)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != historicoSenha.CdHistoricosenha)
            {
                return BadRequest();
            }

            _context.Entry(historicoSenha).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!HistoricoSenhaExists(id))
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

        // POST: api/HistoricoSenhas
        [HttpPost]
        public async Task<IActionResult> PostHistoricoSenha([FromBody] HistoricoSenha historicoSenha)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.HistoricoSenha.Add(historicoSenha);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetHistoricoSenha", new { id = historicoSenha.CdHistoricosenha }, historicoSenha);
        }

        // DELETE: api/HistoricoSenhas/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteHistoricoSenha([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var historicoSenha = await _context.HistoricoSenha.FindAsync(id);
            if (historicoSenha == null)
            {
                return NotFound();
            }

            _context.HistoricoSenha.Remove(historicoSenha);
            await _context.SaveChangesAsync();

            return Ok(historicoSenha);
        }

        private bool HistoricoSenhaExists(int id)
        {
            return _context.HistoricoSenha.Any(e => e.CdHistoricosenha == id);
        }
    }
}